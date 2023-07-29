import random
import json

items = [
    {"name": "Kellogg's Corn Flakes 750g", "price": 2.30},
    {"name": "Heinz Baked Beans 415g", "price": 0.85},
    {"name": "Warburtons Whole Grain Bread 800g", "price": 1.20},
    {"name": "Cadbury Dairy Milk Chocolate Bar 200g", "price": 2.00},
    {"name": "Walkers Classic Variety Crisps 24 Pack", "price": 3.50},
    {"name": "Coca-Cola Original Taste 2L", "price": 2.50},
    {"name": "Nestlé Pure Life Still Spring Water 6x1.5L", "price": 2.75},
    {"name": "Birds Eye 2 Original Chicken Chargrills 180g", "price": 2.00},
    {"name": "McCain Crispy French Fries 900g", "price": 2.10},
    {"name": "Pepsi Max Cherry Cans 8x330ml", "price": 3.50},
    {"name": "John West Tuna Chunks In Brine 160g", "price": 1.50},
    {"name": "Pringles Sour Cream & Onion Crisps 200g", "price": 1.50},
    {"name": "Bisto Favourite Gravy Granules 170g", "price": 1.50},
    {"name": "Birds Eye Garden Peas 800g", "price": 2.00},
    {"name": "PG tips Original Tea Bags 240 pack", "price": 4.50},
    {"name": "Nescafé Gold Blend Instant Coffee 200g", "price": 7.00},
    {"name": "Heinz Cream of Tomato Soup 400g", "price": 0.95},
    {"name": "Ben & Jerry's Cookie Dough Ice Cream 465ml", "price": 4.50},
    {"name": "Hellmann's Real Mayonnaise 400g", "price": 2.15},
    {"name": "Bertolli Original Spread 500g", "price": 1.80},
    {"name": "Fairy Original Washing Up Liquid 433ml", "price": 1.70},
    {"name": "Flash Multi Surface & Floor Cleaner 1L", "price": 2.00},
    {"name": "Andrex Gentle Clean Toilet Tissue 9 Rolls", "price": 4.75},
    {"name": "Finish All in 1 Max Lemon Dishwasher Tablets 31 per pack", "price": 7.00},
]

def generate_receipt(transaction_id, account_id, items):
    num_items = random.randint(1, 5)  # choose a random number of items for this receipt
    receipt_items = random.choices(items, k=num_items)  # choose the items
    for item in receipt_items:
        item["quantity"] = random.randint(1, 3)  # choose a random quantity for each item
    receipt = {
        "transactionId": transaction_id,
        "items": receipt_items,
        "accountId": account_id
    }
    return receipt

def generate_receipts(transaction_ids, account_id, items):
    return [generate_receipt(id, account_id, items) for id in transaction_ids]

def write_receipts_to_file(receipts, filename):
    with open(filename, "w") as f:
        json.dump({"receipts": receipts}, f, indent=4)

def main():
    transaction_ids = ['bd5e0498-21a3-400e-aa2d-1e747c16485f', '32c768dc-071a-4a8b-ab2f-e5d550f43573', '1f3a6f61-2145-481e-acb1-907c2e4ed6ae', '995af1ea-5dc3-4a2c-9273-4c5502ed1bfc', '0c86a288-4cd1-4ca8-a123-ce5b49399f1b', 'bb964cb7-33ca-4061-a56e-910c8c6bc528', '0524c673-3ead-458d-9609-0b4b21490e14', '753724ca-33d3-4736-93e3-d60ac04935c6', '277c4811-db21-4ea6-a5d7-01780ba8e8ad', '07127a79-39c8-40ae-b39a-572a49e6074f', '56cafd49-564d-4945-81a2-a29087b5adad', '72c89c5d-3579-4d17-9058-ac383684e86a', 'cdeed16d-14d1-476e-ab62-6d62aab0f323', '9924a50f-5ff2-4c97-acf6-1c0151fed0a0', 'b81bf36d-5e36-4cd3-a3f1-83d129bc1a0f', '3b2ee518-5a56-4418-9966-bce13ad99661', 'aaaf7ec4-a924-4cff-abfb-ad719292226e', '563395b7-80a2-4e6e-9aae-61915f89ad2a', 'd282e620-e381-4248-9c7a-746f73fbe908', '800e0d62-4739-489f-9af4-bc6bf4868572', '3e40aa67-f45a-405e-8d66-cc529585c1fe', '88c9f432-00d3-4899-9637-a4278761da8f', '0bae626e-fbbd-4db8-8edf-7c55082cf74a', 'a7cb4c16-e365-4de0-bc6f-c733855211d3', '58daca3f-b69a-4d71-af9c-98e64b3c3aa7', 'e8bc0a22-be51-464d-926b-dec1c518014e', 'caaef727-a66a-466b-9ffc-5dea245ce0c1', 'b8ba9d71-272e-480e-a69a-2f6c490eefd2', '7204d375-fe48-4852-8414-8ce6dd27b735', 'af82a17c-6305-4165-bf16-acc2185f6ffe', '01a3f3ad-3d23-40e8-96b9-677e27da95cb', 'a0f78b31-062c-4223-bc0d-ac5d09832301', '43557a75-a5d9-48af-817e-cedfd88ba834', '1ce042a1-7d43-47ad-8650-8e84f36f6462', '5cdeb379-6dfb-491c-9059-acc711d6ed62', '26bbdec4-786c-464a-8a1f-b8b7e467fa4e', '8976ce5a-214e-4079-91df-5b0a72acd44d', 'e77cf3b7-4d1c-429e-9513-9ab903e56dc8', '5340d934-5bc1-47ee-8b03-7d707862b18b', '0f6a0f17-f849-4f6b-aba3-4aaab4aa7cc2', 'f96a5e12-0957-41cb-811d-2b43cad04d6d', 'eaade08a-4aa0-4a54-8a57-0a013dc8e3ee', 'e374c4fd-7e12-401e-bf23-ca788e1a1fd3', 'f7e62722-cc1f-4911-9920-645200987d2e', 'a01ff379-7e76-4875-95c0-ebd64f58583e', 'a5a18445-d5ff-49ad-bda9-76837cf918cd', '2ee87f19-75e8-48c8-8bf6-b252f391d3ae', 'cf930804-6006-49f7-8ecc-239bd59260b8', '9529fd75-67d6-49dc-88b7-3957e938b746', '0188151e-9635-43b8-8825-71481a377e9c', 'd94f93b7-3225-45c9-8390-bb222f90830a', 'd400869b-53bc-4ac3-8638-45dcc8ee88db', '5d6f05fe-3e5f-4544-bd3f-f9db1222ee61', '20cd9f09-6c3b-479f-9a5f-867e81f97e0b', '7ee5c4f1-a8dd-4149-b666-449b3bf2382f', '09218f40-79f1-4ee4-9b5b-d6c03457321d', 'dd8f191f-f3b1-4b5b-8c52-4690633d6863', '3d0af373-5f56-4ea9-a817-b4afd56d440b', '54609b34-0738-413c-89e1-489ff65d87a1', '008b7f93-f21a-4c6f-b3cc-c30bfd64aaf6', '4bf1596d-4437-49d1-a01a-a023f12b5cba', '439290f3-dd0b-49d1-9ed1-f47bd7ccc943']
    account_id = "fb246b90-e549-44ef-831e-ea7fe8cf88c9"
    receipts = generate_receipts(transaction_ids, account_id, items)
    write_receipts_to_file(receipts, "receipts.json")

if __name__ == '__main__':
    main()