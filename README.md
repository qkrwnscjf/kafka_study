
---

## Python - Kafka 기초 실습
- Python으로 간단한 문자열 메시지를 보내고 받는 
- Producer / Consumer 구조 구현(in 로컬)
- `kafka-python` 라이브러리
- 로컬 Kafka 브로커 (Zookeeper 필요 없음)

---

## Java - JSON 데이터 처리 실습

- 백신 접종 센터 API에서 JSON 데이터를 수집(공공데이터포털)
- Kafka Producer가 API로부터 데이터를 받아 전송하고
- Kafka Consumer가 데이터를 수신하여 출력
- 외부 API 데이터와 Kafka 연계 

---

## 수정 계획

- Docker환경에서 실시간 데이터 수집을 이용한 실습
- 추후 Spark, Flink 등을 연계하여 실시간 처리

---

## 참고 사항

- 공공데이터 API 사용 시 발급받은 API 키는 `.env` 등에 별도로 관리
