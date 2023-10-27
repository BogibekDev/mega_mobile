package uz.nlg.mega.utils

import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.Customer
import uz.nlg.mega.model.Product


var OrderProducts = arrayListOf(
    Product(
        id = 1,
        name = "1. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 2,
        name = "2. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 3,
        name = "3. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 4,
        name = "4. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 5,
        name = "5. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 6,
        name = "6. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 7,
        name = "7. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 8,
        name = "8. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 9,
        name = "9. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 10,
        name = "10. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 11,
        name = "11. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 12,
        name = "12. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 13,
        name = "13. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 14,
        name = "14. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 15,
        name = "15. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 16,
        name = "16. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 17,
        name = "17. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 18,
        name = "18. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 19,
        name = "19. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 20,
        name = "20. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 21,
        name = "21. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    ), Product(
        id = 22,
        name = "22. Sayding L-Brus-15x240",
        quantity = 2,
        firstType = "pachka",
        secondType = "dona",
        price = 20_000
    )
)


var Cheques = arrayListOf<Cheque>(
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000,
        customer = Customer(
            id = 1,
            name = "Ogabek Matyakubov",
            phoneNumber = "",
            priceDIff = 0
        )
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Paid,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Returned,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Paid,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Returned,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Returned,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),
    Cheque(
        type = ChequeType.Paid,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = 2000000
    ),

)