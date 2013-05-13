KARAF_VERSION = "2.3.1"

DESCRIPTION = "Apache Karaf ${KARAF_VERSION} - OSGi framework"
PR = "r0"

AUTHOR = "Apache Software Foundation"

SRC_URI = "http://apache.mirrors.tds.net/karaf/${KARAF_VERSION}/apache-karaf-${KARAF_VERSION}.tar.gz  \
	   file://patches/karaf_script.patch"
SRC_URI[md5sum] = "fa4c68ba3f6326e60a0304f7cb4067e0"
SRC_URI[sha256sum] = "45659598bcc5d156872f9edca5b02eb2de5ea5a47d5e745aac234888a8b88f14"


LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f458b88796eec98f53c44c65a3a036e"

RELATIVE_INSTALL_DIR = "/opt/karaf"
INSTALL_DIR = "${D}${RELATIVE_INSTALL_DIR}"

#TODO how to depend on either jre? 6/7
RDEPENDS_${PN} += "openjdk-7-jre"

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

