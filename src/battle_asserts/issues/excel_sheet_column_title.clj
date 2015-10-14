(ns battle-asserts.issues.excel-sheet-column-title
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given a positive integer, return its corresponding column title as appear in an Excel sheet.
                 For example:
                 1 -> A
                 2 -> B
                 3 -> C
                 ...
                 26 -> Z
                 27 -> AA
                 28 -> AB
                 ")

(defn arguments-generator []
  ;NOTE используем (gen/one-of [(gen/choose ...) ...] для более равномерного распределения значений
  ; (1 .. 27) ~ (A .. AA),  (27 .. 703) ~ (AA .. AAA), (703 .. 30000) ~ (AAA .. ARIV)
  (gen/tuple (gen/one-of [(gen/choose 1 27) (gen/choose 27 703) (gen/choose 703 30000)])))

(defn solution [num]
  (if (zero? num)
    ""
    (let [n (dec num) A (int \A) size 26]
      (format "%s%s"
              (->
               n
               (quot size)
               solution)
              (->
               n
               (mod size)
               (+ A)
               char)))))
