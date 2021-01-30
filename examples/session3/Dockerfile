FROM python:3

RUN pip3 install pyyaml

WORKDIR /github/workspace/

ADD . .

ENTRYPOINT ["python3", "yaml2csv.py"]