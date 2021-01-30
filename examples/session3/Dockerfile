FROM python:3

RUN pip3 install pyyaml

WORKDIR /github/workspace/

ADD . .

RUN ls -l /github/workspace/ && pwd && chmod 755 yaml2csv.py

ENTRYPOINT ["python3", "yaml2csv.py"]