import 'package:badges/badges.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:query_app/model/customer.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

import 'database/db_helper.dart';
import 'model/cart_item.dart';

class CartScreen extends StatefulWidget {
  const CartScreen({
    Key? key,
  }) : super(key: key);

  @override
  State<CartScreen> createState() => _CartScreenState();
}

class _CartScreenState extends State<CartScreen> {
  DBHelper? dbHelper = DBHelper();
  List<bool> tapped = [];

  @override
  void initState() {
    super.initState();
    context.read<CartProvider>().getData(new Customer(
        id: 1,
        customerName: '',
        phoneNumber: '',
        crmk: [],
        dcre: [],
        sehy: []));
  }

  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<CartProvider>(context);
    return MaterialApp(
        localizationsDelegates: const [
          GlobalCupertinoLocalizations.delegate,
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
        ],
        supportedLocales: const [
          Locale('ar')
        ],
        locale: const Locale('ar'),
        home: Scaffold(
          appBar: AppBar(
            leading: IconButton(
              icon: const Icon(Icons.arrow_back),
              onPressed: () => Navigator.pop(context, false),
            ),
            backgroundColor: Color.fromARGB(141, 190, 153, 82),
            iconTheme: const IconThemeData(color: Colors.black),
            shadowColor: Colors.black,
            elevation: 50.0,
            centerTitle: true,
            title: const Text('الأصنــاف',
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold)),
            actions: [
              Badge(
                badgeContent: Consumer<CartProvider>(
                  builder: (context, value, child) {
                    return Text(
                      value.getCounter().toString(),
                      style: const TextStyle(
                          color: Colors.white, fontWeight: FontWeight.bold),
                    );
                  },
                ),
                position: const BadgePosition(start: 30, bottom: 30),
                child: IconButton(
                  onPressed: () {},
                  icon: const Icon(Icons.shopping_cart),
                ),
              ),
              const SizedBox(
                width: 20.0,
              ),
            ],
          ),
          body: Column(
            children: [
              Expanded(
                child: Consumer<CartProvider>(
                  builder: (BuildContext context, provider, widget) {
                    if (provider.cart.isEmpty) {
                      return const Center(
                          child: Text(
                        'لا توجد أصناف !',
                        style: const TextStyle(
                            fontStyle: FontStyle.italic,
                            color: Colors.black,
                            fontSize: 18,
                            fontWeight: FontWeight.bold),
                      ));
                    } else {
                      return ListView.builder(
                          shrinkWrap: true,
                          itemCount: provider.cart.length,
                          itemBuilder: (context, index) {
                            return Card(
                              color: Color.fromARGB(255, 175, 171, 137),
                              elevation: 5.0,
                              child: Padding(
                                padding: const EdgeInsets.all(4.0),
                                child: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceEvenly,
                                  mainAxisSize: MainAxisSize.max,
                                  children: [
                                    SizedBox(
                                      width: 600,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
                                          const SizedBox(
                                            height: 5.0,
                                          ),
                                          RichText(
                                            overflow: TextOverflow.ellipsis,
                                            maxLines: 1,
                                            text: TextSpan(
                                                text: 'الصنف: ',
                                                style: TextStyle(
                                                    color: Colors.black,
                                                    fontSize: 16.0),
                                                children: [
                                                  TextSpan(
                                                      text:
                                                          '${provider.cart[index].productName!}\n',
                                                      style: const TextStyle(
                                                          fontWeight:
                                                              FontWeight.bold)),
                                                ]),
                                          ),
                                          RichText(
                                            maxLines: 1,
                                            text: TextSpan(
                                                text: 'الكمية: ',
                                                style: TextStyle(
                                                    color: Colors.black,
                                                    fontSize: 16.0),
                                                children: [
                                                  TextSpan(
                                                      text:
                                                          '${provider.cart[index].quantity!.value}\n',
                                                      style: const TextStyle(
                                                          fontWeight:
                                                              FontWeight.bold)),
                                                ]),
                                          ),
                                          RichText(
                                            maxLines: 1,
                                            text: TextSpan(
                                                text: 'نسبة الخصم: ' r"%",
                                                style: TextStyle(
                                                    color: Colors.black,
                                                    fontSize: 16.0),
                                                children: [
                                                  TextSpan(
                                                      text:
                                                          '${provider.cart[index].discount!}\n',
                                                      style: const TextStyle(
                                                          fontWeight:
                                                              FontWeight.bold)),
                                                ]),
                                          ),
                                          RichText(
                                            maxLines: 1,
                                            text: TextSpan(
                                                text: 'السعر بعد الخصم: ',
                                                style: TextStyle(
                                                    color: Colors.black,
                                                    fontSize: 16.0),
                                                children: [
                                                  TextSpan(
                                                      text:
                                                          '${provider.cart[index].productPrice!.value}\n',
                                                      style: const TextStyle(
                                                          fontWeight:
                                                              FontWeight.bold)),
                                                ]),
                                          ),
                                        ],
                                      ),
                                    ),
                                    ValueListenableBuilder<double>(
                                        valueListenable:
                                            provider.cart[index].quantity!,
                                        builder: (context, val, child) {
                                          if (provider.cart[index].crmkSehy ==
                                              'S') {
                                            return PlusMinusButtons(
                                              addQuantity: () {
                                                cart.addQuantity(
                                                    provider.cart[index].id!);
                                                cart.updatePrice(
                                                    provider.cart[index].id!);
                                                dbHelper!
                                                    .updateQuantity(Cart(
                                                        id: index,
                                                        productId: provider
                                                            .cart[index]
                                                            .productId,
                                                        productName: provider
                                                            .cart[index]
                                                            .productName,
                                                        initialPrice: provider
                                                            .cart[index]
                                                            .initialPrice,
                                                        productPrice: ValueNotifier((provider.cart[index].initialPrice! * provider.cart[index].quantity!.value -
                                                                ((provider.cart[index].initialPrice! *
                                                                    provider
                                                                        .cart[
                                                                            index]
                                                                        .quantity!
                                                                        .value *
                                                                    (provider.cart[index].discount! /
                                                                        100))))
                                                            .ceilToDouble()),
                                                        quantity: ValueNotifier(provider
                                                            .cart[index]
                                                            .quantity!
                                                            .value),
                                                        discount: provider
                                                            .cart[index]
                                                            .discount,
                                                        discountList: 1,
                                                        package: 1,
                                                        priceList: 1,
                                                        crmkSehy: provider
                                                            .cart[index]
                                                            .crmkSehy,
                                                        customerId: provider
                                                            .cart[index]
                                                            .customerId))
                                                    .then((value) {
                                                  setState(() {
                                                    cart.addTotalPrice(
                                                        double.parse(provider
                                                            .cart[index]
                                                            .productPrice
                                                            .toString()));
                                                  });
                                                });
                                              },
                                              deleteQuantity: () {
                                                cart.deleteQuantity(
                                                    provider.cart[index].id!);
                                                cart.removeTotalPrice(
                                                    double.parse(provider
                                                        .cart[index]
                                                        .productPrice
                                                        .toString()));
                                              },
                                              text: val.toString(),
                                            );
                                          } else {
                                            return SizedBox(
                                              width: 1,
                                            );
                                          }
                                        }),
                                    IconButton(
                                        onPressed: () {
                                          dbHelper!.deleteCartItem(
                                              provider.cart[index].id!);
                                          provider.removeItem(
                                              provider.cart[index].id!);
                                          provider.removeCounter();
                                        },
                                        icon: Icon(
                                          Icons.delete,
                                          color: Colors.black,
                                        )),
                                  ],
                                ),
                              ),
                            );
                          });
                    }
                  },
                ),
              ),
              Consumer<CartProvider>(
                builder: (BuildContext context, value, Widget? child) {
                  final ValueNotifier<double?> totalPrice = ValueNotifier(null);
                  for (var element in value.cart) {
                    totalPrice.value =
                        (element.productPrice)!.value + (totalPrice.value ?? 0);
                  }
                  return Column(
                    children: [
                      Container(
                        decoration: BoxDecoration(
                          color: Color.fromARGB(255, 123, 187, 50),
                          border: Border.all(
                            width: 1,
                            color: Colors.black,
                          ),
                        ),
                        child: ValueListenableBuilder<double?>(
                            valueListenable: totalPrice,
                            builder: (context, val, child) {
                              return ReusableWidget(
                                  title: 'الإجمالى',
                                  value:
                                      r'$' + (val?.toStringAsFixed(2) ?? '0'));
                            }),
                      )
                    ],
                  );
                },
              )
            ],
          ),
          bottomNavigationBar: InkWell(
            onTap: () {
              ScaffoldMessenger.of(context).showSnackBar(
                const SnackBar(
                  content: Text('تم إنشاء الطلبية',
                      style: const TextStyle(
                          fontStyle: FontStyle.italic,
                          color: Colors.green,
                          fontWeight: FontWeight.bold)),
                  duration: Duration(seconds: 5),
                ),
              );
            },
            child: ElevatedButton.icon(
              style: ElevatedButton.styleFrom(
                  primary: Color.fromARGB(255, 165, 154, 119),
                  textStyle: TextStyle(
                      fontWeight: FontWeight.bold, color: Colors.black)),
              onPressed: () {
                dbHelper?.deleteAllCartItem();
                cart.removeAllCounter();
                cart.removeAllTotalPrice();
                cart.removeQuantity();
                cart.removeAllItems();
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(
                    content: Text('تم إنشاء الطلبية',
                        style: const TextStyle(
                            fontStyle: FontStyle.italic,
                            color: Colors.green,
                            fontWeight: FontWeight.bold)),
                    duration: Duration(seconds: 5),
                  ),
                );
              },
              icon: Icon(Icons.create, color: Colors.black, size: 20),
              label: Text('إنشاء طلبية',
                  style: TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.w900,
                      fontSize: 20)),
            ),
          ),
        ));
  }
}

class PlusMinusButtons extends StatelessWidget {
  final VoidCallback deleteQuantity;
  final VoidCallback addQuantity;
  final String text;
  const PlusMinusButtons(
      {Key? key,
      required this.addQuantity,
      required this.deleteQuantity,
      required this.text})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        IconButton(onPressed: deleteQuantity, icon: const Icon(Icons.remove)),
        Text(text),
        IconButton(onPressed: addQuantity, icon: const Icon(Icons.add)),
      ],
    );
  }
}

class ReusableWidget extends StatelessWidget {
  final String title, value;
  const ReusableWidget({Key? key, required this.title, required this.value});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            title,
            style: TextStyle(
                color: Colors.black, fontSize: 20, fontWeight: FontWeight.w500),
          ),
          Text(
            value.toString(),
            style: TextStyle(
                color: Colors.black, fontSize: 20, fontWeight: FontWeight.w500),
          ),
        ],
      ),
    );
  }
}
