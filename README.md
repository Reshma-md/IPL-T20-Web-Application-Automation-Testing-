IPL Web Automation Framework
-----------------------------------------------------------------------------
This repository contains a robust, end-to-end automation suite for the IPL (Indian Premier League) web portal. Built using Java, Selenium WebDriver, and TestNG, this project implements a Modular Page Object Model (POM) to ensure scalability, reliability, and ease of maintenance.

1.Project Architecture
The framework follows a layered architecture to decouple test logic from UI elements
Base Layer: Manages WebDriver lifecycle and global configurations.
Page Layer (POM): Encapsulates web elements and page-specific actions using PageFactory.
Test Layer: Contains business logic and assertions using TestNG.
Resource Layer: Orchestrates execution via testng.xml and manages dependencies via pom.xml.

2.Technical Stack

Language: Java 11/17 
Automation: Selenium WebDriver 4.x 
Test Framework: TestNG 
Build Tool: Maven 
Design Pattern: Page Object Model (POM) 
Logging: SLF4J

3.Key Features & Implementation

a)Dynamic Data Handling: Implemented "Hover-and-Extract" logic using the Actions class for tooltip data.
b)Relative Locators: Used following-sibling axis XPaths for dynamic table synchronization in the Points Table.
c)Operational Robustness: Integrated a "Smart-Cookie" handler to manage asynchronous overlays.
d)Resource Optimization: Utilized a Custom Driver Factory to manage browser instances efficiently

4.Test Scenarios

ID,Test Case,Focus Area,Status
TC_01,Footer Link Integrity,Validating 29+ links across 4 sections.+1,✅ PASS
TC_02,Team Logo & Trophies,Hover-based verification for 10 teams.+1,✅ PASS
TC_03,Points Table Sync,Real-time data validation for Rank 1.,✅ PASS
TC_04,Global Search,Indexing verification and banner handling.+1,✅ PASS


5.Team Roles & Contributions

Mupparaju Sai Ram: Framework Backbone, BaseTest setup, and Points Table synchronization logic.
Mahammad Reshma: Interactive UI Components, Actions class workflows, and Global Search module.
