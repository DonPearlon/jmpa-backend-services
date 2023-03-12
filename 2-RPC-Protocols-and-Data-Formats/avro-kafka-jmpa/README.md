## Order 
- Order can be published via making POST request to "/order" endpoint
- Body example:
{
  "id": 1,
  "customerId": 1,
  "customer": "Customer1",
  "orderItems": [
    {
      "id": 1,
      "name": "Item1",
      "price": 10.0
    },
    {
     "id": 2,
     "name": "Item2",
     "price": 20.0
    },
    {
      "id": 3,
      "name": "Item3",
      "price": 30.0
    }
  ]
}