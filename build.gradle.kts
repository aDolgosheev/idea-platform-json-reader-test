plugins {
    id("java")
    application
}

group = "ru.dolgosheev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "ru.dolgosheev.App"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "ru.dolgosheev.App"
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

tasks.test {
    useJUnitPlatform()
}
