(ns opencensus-clojure.reporting.zipkin
  (:require [clojure.tools.logging :as log])
  (:import (io.opencensus.exporter.trace.zipkin ZipkinTraceExporter)))

(defn report
  "Reports the spans to Zipkin.

    - `:endpoint` optional endpoint to report to
    - `:service-name` the name of your app. This is global and forever."
  ([^String service-name] (report "http://localhost:9411/api/v2/spans" service-name))
  ([^String endpoint ^String service-name] (do
                             (log/info "starting Zipkin reporter")
                             (ZipkinTraceExporter/createAndRegister endpoint service-name))))

(defn shutdown []
  "Unregisters the trace exporter"
  (ZipkinTraceExporter/unregister))
