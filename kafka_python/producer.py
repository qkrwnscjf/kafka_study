from kafka import KafkaProducer
import time

producer = KafkaProducer(bootstrap_servers='localhost:9092')
arr = ['박', '준', '철']

for i in arr:
    message = f"message {i}"
    producer.send('my-topic', message.encode('utf-8'))
    print(f"Sent: {message}")
    time.sleep(1)  # 1초 간격으로 전송

producer.flush()
producer.close()

