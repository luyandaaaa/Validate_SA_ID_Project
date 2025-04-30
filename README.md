
# South African ID Number Validator

[![Kotlin](https://img.shields.io/badge/kotlin-1.8.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A Kotlin/Java library for validating South African ID numbers according to official specifications.

## 📋 Table of Contents

- [Features](#-features)
- [Usage](#-usage)
- [Validation Rules](#-validation-rules)
- [Testing](#-testing)
- [License](#-license)

## ✨ Features

- ✅ Full validation of 13-digit SA ID numbers
- 📅 Date validation with leap year support
- 🧑‍🤝‍🧑 Citizenship status verification
- ♀️♂️ Gender digit validation
- 🔢 Luhn algorithm checksum verification
- 🐛 Detailed error reporting
- 🧪 100% test coverage

## 🔍 Validation Rules
Validates all components of SA ID format YYMMDDSSSSCAZ:

Component	Validation Rule
Length	Exactly 13 digits
Format	Only numeric characters (0-9)
Date (YYMMDD)	Valid calendar date with leap year support
Gender (SSSS)	0000-4999 (F), 5000-9999 (M)
Citizenship (C)	0 (citizen) or 1 (permanent resident)
Checksum (Z)	Valid Luhn algorithm checksum

## 🧪 Testing
Run tests with:

bash
./gradlew test

## 📜 License
Distributed under the MIT License. See LICENSE for more information.
