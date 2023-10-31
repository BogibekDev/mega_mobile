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
            name = "Bogibek Matyaqubov",
            phoneNumber = "+998942344432",
            priceDiff = 0,
            surname = "Bogibek Matyaqubov"
        )
    ),
    Cheque(
        type = ChequeType.Saved,
        serialNumber = 155466,
        clientName = "Bogibek Matyaqubov",
        date = "12.12.2023",
        time = "15:00",
        products = OrderProducts,
        totalPrice = -2000000
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

val Customers = arrayListOf(
    Customer(
        id = 1,
        name = "Ogabek Matyakubov",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = 125000000,
        surname = "Matyakubov"
    ),
    Customer(
        id = 2,
        name = "Bogibek Matyakubov",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = 0,
        surname = "Matyakubov"
    ),
    Customer(
        id = 3,
        name = "Donimman umuman",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = 354500,
        surname = "umuman"
    ),
    Customer(
        id = 4,
        name = "Ullatmi",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = -200000,
        surname = "jo'rasi"
    ),
    Customer(
        id = 5,
        name = "Anasini qizi",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = 0,
        surname = "qizi"
    ),
    Customer(
        id = 6,
        name = "Ammami axtiqi",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = 0,
        surname = "axtiqi"
    ),
    Customer(
        id = 7,
        name = "Maqsud shopir",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = 232400,
        surname = "shopir"
    ),
    Customer(
        id = 8,
        name = "Zerip qiziqchi",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = -45300,
        surname = "qiziqchi"
    ),
    Customer(
        id = 9,
        name = "Sappi sanach",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = -3545000,
        surname = "sanach"
    ),
    Customer(
        id = 10,
        name = "Ravil aka",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = -3545000,
        surname = "aka"
    ),
    Customer(
        id = 11,
        name = "Qummi dalbayop",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = -123000,
        surname = "dalbayop"
    ),
    Customer(
        id = 12,
        name = "Atamagagan Boztopraq",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = 400000,
        surname = "Boztopraq"
    ),
    Customer(
        id = 13,
        name = "Bogibek Matyakubov",
        phoneNumber = "+998 93 203 73 13",
        priceDiff = 12500,
        surname = "Matyakubov"
    )
)