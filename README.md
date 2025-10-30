# Web UI Test Automation with Cucumber, Selenium, and Gradle (Java)

## Struktur Proyek
.
├── build.gradle
├── report/
│   ├── cucumber.html
│   └── cucumber.jcon
├── src
│   └── test
│       ├── java
│       │   └── com
│       │       └── zaenal
│       │           ├── page
│       │           │   ├── LoginPage.java
│       │           │   └── InventoryPage.java
│       │           ├── stepdef
│       │           │   ├── LoginStepDef.java
│       │           │   └── InventoryStepDef.java 
│       │           ├──BaseTest.java                    #Inisialisasi WebDriver
│       │           └──CucumberTest.java
│       └── resources
│           └── features                                #Skenario pengujian
│               ├── login.feature
│               └── inventory.feature
└── README.md

## Teknologi yang digunakan
* Java
* Selenium WebDriver
* Cucumber
* Gradle
* JUnit
* Dikembangkan dan dijalankan menggunakan IntelliJ IDEA

## Skenario test
Framework ini dirancang untuk menguji web SauceDemo pada
fitur login, melihat produk, menambahkan, dan menghapus item dari keranjang.

## Laporan hasil test
Laporan tersedia: 
build/reports/cucumber.html

## Kontributor
Zaenal 