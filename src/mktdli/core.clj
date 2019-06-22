(ns mktdli.core
  (:gen-class))

(defn collect-lines [a]
  (let [l (read-line)]
    (cond
      (= l "") (str a "")
      :else (collect-lines (str a (str "  - " l "\n"))))))

(defn mktdlimain []
  (do
    (println "what are your to-dos for tomorrow?\n")
    (let [tomorrow
          (-> (java.time.LocalDate/parse
               (.format (java.text.SimpleDateFormat. "yyyy-MM-dd")
                        (new java.util.Date))) (.plusDays 1))
          todos
          (collect-lines (str "To-dos for " tomorrow ":\n\n"))
	  fname
	  (str (System/getProperty "user.home")
                 "/Documents/to-dos/"
                 tomorrow
                 ".todo")]
      (clojure.java.io/make-parents fname)
      (spit fname todos))))

(defn -main []
  (mktdlimain))
