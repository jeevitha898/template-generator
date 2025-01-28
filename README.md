# EasyGo Template Generator

## Overview
The **EasyGo Template Generator** is a desktop application designed to simplify and automate the process of configuring switches. This project was developed while working as an administrator at BHEL, specifically for the department I was associated with. The goal of the application is to reduce manual effort and save time when programming multiple switches by automating repetitive tasks.

## Problem Statement
In the original manual process, each switch configuration required:
- Setting unique values for **hostname**, **switch name**, **IP address**, and **subnet**.
- Repeating the process for every switch, even though the template file structure remained consistent.

This was a time-consuming and error-prone task due to the repetitive nature of string replacements.
![Alt text](image-path)
## Solution
The **EasyGo Template Generator** automates the above process by:
- Allowing users to input unique values for hostname, switch name, IP address, and subnet.
- Automatically generating the updated configuration file for each switch.
- Using basic string manipulation techniques (string find and replacement) to efficiently handle the updates.

This results in a significant reduction in the time and effort required to configure multiple switches.
![Alt Text](TGimage.png)

## Example
### Input Template
```plaintext
hostname [hostname_placeholder]
switch [switch_placeholder]
ip address [ip_address_placeholder]
subnet mask [subnet_placeholder]
```

### User Input
- Hostname: `A1-SW201`
- Switch name: `Switch_1`
- IP address: `192.168.1.1`
- Subnet mask: `255.255.255.0`

### Generated Output
```plaintext
hostname A1-SW201
switch Switch_1
ip address 192.168.1.1
subnet mask 255.255.255.0
```

## Key Features
- **Template Customization**: Provides a base template for users to configure switches.
- **Automated Replacements**: Replaces placeholders with user-provided data.
- **Batch Processing**: Generates configurations for multiple switches in one go.

## Technical Details
- **Programming Language**: Java
- **Core Functionality**: String find and replace operations.
- **User Interface**: A desktop application with an intuitive GUI.

## Benefits
- Eliminates repetitive manual tasks.
- Reduces configuration errors.
- Speeds up the switch programming process.

## Usage
1. Launch the application.
2. Provide the input template file.
3. Enter unique values for hostname, switch name, IP address, and subnet.
4. Generate the updated configuration files.
5. Save and deploy the configurations to the switches.

