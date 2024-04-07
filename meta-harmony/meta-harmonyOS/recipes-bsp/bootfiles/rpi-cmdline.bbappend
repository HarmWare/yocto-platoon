# Edit the cmdline 
CMDLINE_ROOTFS:remove = "root=/dev/mmcblk0p2 rootfstype=ext4"
CMDLINE_ROOTFS:append = "root=/dev/nfs ip=dhcp nfsroot=192.168.1.11:/home/mohamed/nfsroot,v3,tcp"

