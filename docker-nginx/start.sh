#!/bin/bash

/etc/init.d/nginx start
tail -100f /var/log/nginx/error.log
