curl -i -X POST \
  --url http://127.0.0.1:8001/services/ \
  --data 'name=history' \
  --data 'url=http://history-service:8090/history'

curl -i -X POST \
  --url http://localhost:8001/services/history/routes \
  --data 'paths[]=/history'

curl -i -X POST \
  --url http://127.0.0.1:8001/services/ \
  --data 'name=pay' \
  --data 'url=http://payment-service:8080/pay'

curl -i -X POST \
  --url http://localhost:8001/services/pay/routes \
  --data 'paths[]=/pay'
