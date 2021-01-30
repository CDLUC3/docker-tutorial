FROM python:3

RUN pip3 install pyyaml

WORKDIR /app

ADD . .

ENTRYPOINT ["python3", "/app/yaml2csv.py"]