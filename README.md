# Aplikasi Invoice Management #

Aplikasi ini dipakai untuk mengelola invoice dan menyambungkan dengan berbagai metode pembayaran masa kini.
Diantara metode pembayaran yang akan disupport antara lain :

* Virtual Account Bank

  * Bank BNI
  * Bank CIMB
  * Bank BSI

* e-Wallet

  * Ovo
  * Gopay

* QR Payment

  * QRIS


# Setup Database #

1. Run postgresql on docker
 
  ```
  docker run --rm \
    --name invoice-db \
    -e POSTGRES_DB=invoicedb \
    -e POSTGRES_USER=invoice \
    -e POSTGRES_PASSWORD=MGv5vumHqQvhWdKQoCHW \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v "$PWD/invoicedb-data:/var/lib/postgresql/data" \
    -p 5432:5432 \
    postgres:13  
  ```

* db.migration name versioning use calver.org

