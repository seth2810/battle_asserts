(ns battle-asserts.issues.leaders-in-an-array-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.leaders-in-an-array :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (last (apply issue/solution v))
                   (last (first v)))))

(ct/defspec spec-signature
  20
  (prop/for-all [v (issue/arguments-generator)]
                (true? (h/generate-signatures issue/signature v))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
