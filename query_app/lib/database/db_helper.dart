import 'package:query_app/model/customer.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path_provider/path_provider.dart';
import 'package:path/path.dart';
import 'dart:io' as io;

import '../model/cart_item.dart';

class DBHelper {
  static Database? _database;

  Future<Database?> get database async {
    if (_database != null) {
      return _database!;
    }
    _database = await initDatabase();
    return _database;
  }

  initDatabase() async {
    io.Directory directory = await getApplicationDocumentsDirectory();
    String path = join(directory.path, 'cart.db');
    var db = await openDatabase(path, version: 1, onCreate: _onCreate);
    return db;
  }

  _onCreate(Database db, int version) async {
    await db.execute(
        'CREATE TABLE customer(id INTEGER PRIMARY KEY AUTOINCREMENT, customerName TEXT, phoneNumber VARCHAR)');
    await db.execute(
        'CREATE TABLE cart(id INTEGER PRIMARY KEY AUTOINCREMENT,productId VARCHAR,productName TEXT,initialPrice DOUBLE,discount DOUBLE,productPrice DOUBLE,quantity DOUBLE,package DOUBLE,priceList INTEGER,discountList INTEGER,crmkSehy VARCHAR,customerId INTEGER,FOREIGN KEY (customerId) REFERENCES customer(id) ON DELETE CASCADE)');
  }

  Future<Cart> insertCart(Cart cart) async {
    var dbClient = await database;
    await dbClient?.insert('cart', cart.toMap());
    return cart;
  }

  Future<Customer> insertCustomer(Customer customer) async {
    var dbClient = await database;
    await dbClient!.insert('customer', customer.toMap());
    return customer;
  }

  Future<List<Cart>> getCartList(Customer customer) async {
    var dbClient = await database;
    final List<Map<String, Object?>> queryResult =
        await dbClient!.query('cart');
    return queryResult.map((result) => Cart.fromMap(result)).toList();
  }

  Future<int> deleteCartItem(int id) async {
    var dbClient = await database;
    return await dbClient!.delete('cart', where: 'id = ?', whereArgs: [id]);
  }

  Future<int> deleteCustomer(int id) async {
    var dbClient = await database;
    return await dbClient!.delete('customer', where: 'id = ?', whereArgs: [id]);
  }

  Future<int> updateQuantity(Cart cart) async {
    var dbClient = await database;
    return await dbClient!.update('cart', cart.quantityMap(),
        where: "productId = ?", whereArgs: [cart.productId]);
  }

  Future<List<Cart>> getCartId(int id) async {
    var dbClient = await database;
    final List<Map<String, Object?>> queryIdResult =
        await dbClient!.query('cart', where: 'id = ?', whereArgs: [id]);
    return queryIdResult.map((e) => Cart.fromMap(e)).toList();
  }

  Future<int> deleteAllCartItem() async {
    var dbClient = await database;
    return await dbClient!.delete('cart');
  }
}
