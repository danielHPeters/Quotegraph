group = "ch.peters.daniel"
version = "1.0"

plugins {
  id("org.jetbrains.kotlin.jvm") version "1.3.20"
  application
  groovy
}

application {
  applicationName = "quotegraph"
  mainClassName = "$group.$applicationName.App"
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.20")
  testImplementation("org.codehaus.groovy:groovy-all:2.5.5")
  testImplementation("org.spockframework:spock-core:1.2-groovy-2.5")
  testImplementation("junit:junit:4.12")
}

repositories {
  jcenter()
}
