# Point of Sale (POS) System

A simple yet functional Java-based Point of Sale (POS) system designed for billing and revenue management. This project allows users to create new bills, resume pending bills, calculate discounts, and generate revenue reports.

---

## Features

- 📊 **Bill Management**
  - Create new bills
  - Save and load pending bills
  - Print detailed bill summaries

- 📒 **Item Management**
  - Read item details (item code, name, unit price, discount percentage) from a CSV file

- 📊 **Revenue Report**
  - Generate reports showing total discounts given and net revenue collected

- ⚡ **Persistence**
  - Serialize and deserialize bill objects to resume transactions later

---

## File Structure

```
├── pos_.java
├── super_saving_items.csv
├── bill.txt (generated after saving bills)
├── pending_bill.ser (auto-generated serialized bill)
└── README.md
```

---

## Prerequisites

- Java JDK 8 or higher installed
- Basic understanding of command line usage

---

## Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/yourusername/pos-system.git
cd pos-system
```

2. **Prepare your CSV file**

Create a `super_saving_items.csv` file in the same directory. Example content:

```
item_code,item_name,unit_price,x,x,x,x,per_discount
101,Soap,30,_,_,_,_,10
102,Shampoo,100,_,_,_,_,15
103,Toothpaste,50,_,_,_,_,5
```

(*Note: Replace underscores `_` with dummy values or keep empty. Only `item_code`, `item_name`, `unit_price`, and `per_discount` are used.)*

3. **Compile the Java program**

```bash
javac pos_.java
```

4. **Run the program**

```bash
java pos_
```

---

## How to Use

### Menu Options

- **1. New bill**
  - Start a new transaction
  - Enter cashier and customer names
  - Add items by entering item codes and quantities
  - Save or print the bill

- **2. Pending bill**
  - Resume the last saved (pending) transaction

- **3. Generate revenue report**
  - Display the total discount and total net revenue collected so far

### During Billing

- Enter item codes and quantity.
- Enter `save` to temporarily save the current bill (serialization).
- Leave input blank to finalize and print the bill.

---

## Important Notes

- Always ensure the `super_saving_items.csv` file is present before starting.
- Bills are saved in `bill.txt`.
- Pending bills are saved in `pending_bill.ser`.
- This is a console-based application; no GUI is implemented.

---

## Future Enhancements (Ideas)

- GUI Interface with JavaFX or Swing
- Database Integration (MySQL/PostgreSQL)
- Item search and filter
- Discount coupons and offers
- User authentication system

---

## License

This project is licensed under the MIT License. Feel free to use, modify, and distribute it.

---

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

Thank you for checking out the POS System! Happy billing! 🌟
