require harmony-base.inc

DESCRIPTION = "Release image for Raspberry Pi 3"
SECTION = "Release"

IMAGE_INSTALL:append = " mqttclient paho-mqtt-cpp boost"
