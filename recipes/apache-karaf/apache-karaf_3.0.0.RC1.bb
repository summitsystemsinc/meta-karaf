KARAF_VERSION = "3.0.0.RC1"

DESCRIPTION = "Apache Karaf ${KARAF_VERSION} - OSGi framework"
PR = "r0"

AUTHOR = "Apache Software Foundation"

SRC_URI = "http://apache.mirrors.tds.net/karaf/3.0.0.RC1/apache-karaf-3.0.0.RC1.tar.gz \
	   file://patches/karaf_script.patch"
SRC_URI[md5sum] = "734589af4d08090a4685de1abe01cffa"
SRC_URI[sha256sum] = "5970bf1777c1dcc5a57deb6162130197df2659e41e12a3274034f16572e5cd47"


LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f458b88796eec98f53c44c65a3a036e"

RELATIVE_INSTALL_DIR = "/opt/karaf"
INSTALL_DIR = "${D}${RELATIVE_INSTALL_DIR}"

#TODO how to depend on either jre? 6/7
RDEPENDS = "openjdk-7-jre"

FILES_${PN} = "\
	${RELATIVE_INSTALL_DIR}/*"

do_install() {
	install -d ${INSTALL_DIR}

	cd ${S}
	find . -type d -exec install -d ${INSTALL_DIR}/{} \;
	find . -type f -exec install {} ${INSTALL_DIR}/{} \;

	cd ${WORKDIR}
	
}

do_install_append() {
	#Remove the extra junk...
	rm -rfv ${INSTALL_DIR}/bin/*.bat
	#Where do these come from? (The patching process...)
	rm -rfv ${INSTALL_DIR}/.pc
}

