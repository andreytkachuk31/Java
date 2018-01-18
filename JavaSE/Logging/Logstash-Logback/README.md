Logstash
======================

# Description
An example of a java maven application which logs to logstash. Logstash is configured with log4j input.
Works only for log4j 1.x versions

## Download logstash
from http://logstash.net

##Start logstash
bin/logstash -f ./conf/{config_file}

#Example logstash config file
```json
input {
  tcp {
    type => "tcp_log"
    host => "127.0.0.1"
    port => 4560
  }
}

output {
  stdout {}
}
```

