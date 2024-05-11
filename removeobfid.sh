#!/bin/sh
find . -type f -exec sed -i '/__OBFID/d' {} +
