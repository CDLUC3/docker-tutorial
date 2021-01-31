#!/usr/bin/python

# pip3 install pyyaml
import os
import yaml
import sys

class MyConverter:

    def parseYaml(self, f):
        config = self.loadYaml(f)
        return config

    def loadYaml(self, f):
        with open(f, 'r') as stream:
            try:
                return yaml.safe_load(stream)
            except yaml.YAMLError as exc:
                print(exc)
                exit("Yaml load error")

    def processContacts(self, data):
        families = data['families']
        for key in families:
            fjson = families[key]
            f = Family(key, fjson)
            f.processFamily()

class Family:
    def __init__(self, key, json):
        self.key = key
        self.members = []
        for pjson in json:
            self.members.append(Person(pjson))

    def processFamily(self):
        print("\n\tFamily {}\n".format(self.key))
        fname = "output/{}.txt".format(self.key)
        f = open(fname, "w+")
        print(os.path.abspath(fname))
        for p in self.members:
            p.printrec(f, 0)
        f.close()

class Person:
    def __init__(self, json):
        self.first_name = json.get('first_name', '')
        self.last_name = json.get('last_name', '')
        self.phone = json.get('phone', [])
        self.email = json.get('email', [])

    def rec_count(self):
        m = max(len(self.email), len(self.phone))
        m = max(1, m)
        return m

    def printrec(self, f, i):
        rec = "{},{},{},{}".format(
            self.first_name,
            self.last_name,
            self.email[i] if len(self.email) > i else '',
            self.phone[i] if len(self.phone) > i else ''
        )
        print(rec)
        f.write(rec)
        f.write("\n")
        if (i + 1 < self.rec_count()):
            self.printrec(f, i+1) 


myConv = MyConverter()
arg = sys.argv[1] if len(sys.argv) > 1 else 'contacts.yml'
data = myConv.parseYaml(arg)
myConv.processContacts(data)