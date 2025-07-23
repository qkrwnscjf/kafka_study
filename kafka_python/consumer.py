from kafka import KafkaConsumer

consumer = KafkaConsumer(
    'my-topic',
    bootstrap_servers=['localhost:9092'],
    auto_offset_reset='earliest',
    enable_auto_commit=True,
    group_id='my-group',
    consumer_timeout_ms=10000   # 10초 대기 (더 길게 설정해도 됨)
)


print("Consumer started...")
for message in consumer:
    print(f"Received: {message.value.decode('utf-8')}")

