(ns clj-jms-activemq-fifo-reader.main
  (:use clojure.pprint
        [clojure.string :only [join split]]
        clojure.tools.cli
        clj-assorted-utils.util)
  (:require (clj-jms-activemq-toolkit [jms :as activemq]))
  (:gen-class))

(defn -main [& args]
  (let [cli-args (cli args
                      ["-d" "--daemon" "Run as daemon." :flag true :default false]
                      ["-u" "--url" 
                       "URL to connect to the broker." 
                       :default "tcp://localhost:61616"]
                      ["-t" "--topic"
                       "Name of the topic to which the data is sent."
                       :default "test.fifo.topic"]
                      ["-f" "--file"
                       "Name of the file/fifo from where the data is read."
                       :default "test.fifo"]
                      ["-h" "--help" "Print this help." :flag true])
        arg-map (cli-args 0)
        extra-args (cli-args 1)
        help-string (cli-args 2)]
    (when (arg-map :help)
      (println help-string)
      (System/exit 0))
    (let [url (arg-map :url)
          topic (str "/topic/" (arg-map :topic))
          in-file (arg-map :file)
          producer (activemq/create-producer url topic)
          lineseq-rdr (create-threaded-lineseq-reader in-file (fn [l] (producer l)))
          shutdown-fn (fn []
                        (producer :close)
                        (lineseq-rdr))]
      (if (:daemon arg-map)
            (-> (agent 0) (await))
            (do
              (println "FIFO reader started... Type \"q\" followed by <Return> to quit: ")
              (while (not= "q" (read-line))
                (println "Type \"q\" followed by <Return> to quit: "))
              (println "Shutting down...")
              (shutdown-fn))))))

