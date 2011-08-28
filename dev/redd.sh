#!/bin/bash
# 
# EMR bootstrap task for our gdal stuff.

set -e

bucket=reddconfig
fwtools=FWTools-linux-x86_64-4.0.0.tar.gz
native=linuxnative.tar.gz

wget -S -T 10 -t 5 http://$bucket.s3.amazonaws.com/$fwtools
sudo mkdir -p /usr/local/fwtools
sudo tar -C /usr/local/fwtools -xzf $fwtools

wget -S -T 10 -t 5 http://$bucket.s3.amazonaws.com/$native
sudo mkdir -p /home/hadoop/native
sudo tar -C /home/hadoop/native -xzf $native

echo "export LD_LIBRARY_PATH=/usr/local/fwtools/usr/lib" >> /home/hadoop/conf/hadoop-user-env.sh
