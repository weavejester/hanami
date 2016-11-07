(ns hanami.core
  (:require [clojure.string :as str])
  (:import [java.net URI URLEncoder]))

(defmulti jdbc-uri
  "Return the JDBC URI for the supplied Heroku URI."
  {:arglists '([uri])}
  (fn [uri & args] (keyword (.getScheme (URI. uri)))))

(defmethod jdbc-uri :postgres [uri & {:keys [ssl]}]
  (let [uri (URI. uri)]
    (str "jdbc:postgresql://"
         (.getHost uri)
         (if (not= (.getPort uri) -1)
           (str ":" (.getPort uri)))
         (.getPath uri)
         (if-let [[user pass] (some-> uri .getUserInfo (str/split #":"))]
           (str (if ssl "?sslmode=require&" "?")
                "user="     (URLEncoder/encode user)
                "&password=" (URLEncoder/encode pass))))))
