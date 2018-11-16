DESCRIPTION = "Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS = "lzop-native"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=${KBRANCH}"

O_KERNEL_CONFIG_odroid-xu3  = "odroid-xu3"
O_KERNEL_CONFIG_odroid-xu4  = "odroid-xu4"
O_KERNEL_CONFIG_odroid-xu3-lite  = "odroid-xu3"
O_KERNEL_CONFIG_odroid-hc1  = "odroid-hc1"
O_KERNEL_CONFIG_odroid-c2  = "odroid-c2"
O_KERNEL_CONFIG_odroid-c1  = "odroid-c1"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRC_URI += "file://${O_KERNEL_CONFIG}/defconfig"

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONF_BSP_AUDIT_LEVEL = "0"

COMPATIBLE_MACHINE = "(odroid-c1|odroid-c2|odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-hc1)"


