FROM ubuntu:latest
LABEL authors="hoabo"

ENTRYPOINT ["top", "-b"]
# 4