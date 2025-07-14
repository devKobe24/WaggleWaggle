#!/bin/bash

# Elastic Beanstalk 환경 변수에서 DB 호스트 정보 가져오기
DB_HOST=$(/opt/elasticbeanstalk/bin/get-config environment -k DB_HOST)
DB_PORT=$(/opt/elasticbeanstalk/bin/get-config environment -k DB_PORT)

LOG_FILE=/var/log/db_connection_test.log

echo "--- DB Connection Test --- (Date: $(date))" > $LOG_FILE
echo "Attempting to connect to $DB_HOST on port $DB_PORT..." >> $LOG_FILE

# nc(netcat) 명령어를 사용하여 10초 타임아웃으로 TCP 연결 테스트
# -z: 포트 스캔 모드, -v: 상세 정보 출력, -w10: 타임아웃 10초
nc -z -v -w10 $DB_HOST $DB_PORT >> $LOG_FILE 2>&1

# 결과 확인
if [ $? -eq 0 ]; then
  echo "SUCCESS: Connection to $DB_HOST:$DB_PORT was successful." >> $LOG_FILE
else
  echo "FAILURE: Failed to connect to $DB_HOST:$DB_PORT." >> $LOG_FILE
fi

echo "--- Test End ---" >> $LOG_FILE

# 스크립트가 정상적으로 실행되도록 권한 부여
chmod 644 $LOG_FILE
