(ns hanami.core-test
  (:require [clojure.test :refer :all]
            [hanami.core :refer :all]))

(deftest test-jdbc-uri
  (testing "postgres"
    (is (= (jdbc-uri "postgres://localhost/postgres")
           "jdbc:postgresql://localhost/postgres"))
    (is (= (jdbc-uri "postgres://foo:foo@heroku.com/hellodb")
           "jdbc:postgresql://heroku.com/hellodb?user=foo&password=foo"))
    (is (= (jdbc-uri "postgres://foo:foo@heroku.com:5432/hellodb")
           "jdbc:postgresql://heroku.com:5432/hellodb?user=foo&password=foo"))))
