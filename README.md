# MFS File System

MFS (Mobile Financial System) is a simple Java-based command-line application that simulates a mobile financial service. This system allows users to manage their accounts, deposit and withdraw money, transfer funds between users, view mini-statements, and more. The data is stored and retrieved using text files to mimic a basic database system.

## Features

- User Registration: New users can be registered with a phone number, name, and a secure PIN. Default balance of 500 Taka is assigned upon registration.
- Deposit & Withdraw: Users can deposit up to 30,000 Taka at a time and cannot hold more than 100,000 Taka. Withdrawals can be made based on the available balance.
- Balance Transfer: Users can transfer money to other registered users. If a recipient is not found, the transfer is not allowed.
- My Account: Users can view their name, phone number, and current balance. The option to reset the PIN is also available.
- Mini Statement: Users can print and view their transaction history by date and phone number.
- Data Persistence: The system saves user credentials and transaction history in text files (`users.txt` and `transactions.txt`).

## Getting Started

### Prerequisites

- Java Development Kit (JDK 8 or later)
- A terminal/command line interface

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/MFS.git
   cd MFS
