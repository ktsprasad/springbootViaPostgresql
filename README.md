# Spring Boot Integration With PostgreSQL as a Maven Project


## Setup a test username, password and database
* Open the terminal and follow the below commands
*  To start postgresql@14 now and restart at login: brew services start postgresql@14
* Or, if you don't want/need a background service you can just run: /usr/local/opt/postgresql@14/bin/postgres -D /usr/local/var/postgresql@14
* itpltestmacbook@INL-C02YQF9FLVCF ~ %   initdb --locale=C -E UTF-8 /usr/local/var/postgresql@14
* Postgres Settings: user: app_user, password: app_password, and database: app_database
* To create the above Postgres setting follow the below commands
* Get the sql mode by this cmd: psql postgres
* Create user cmd: CREATE ROLE app_user WITH PASSWORD ‘app_password’;
* Assign the role cmd: ALTER ROLE app_user CREATEDB;
* Exit using cmd: \q
* Login with new user cmd: psql postgres -U app_user
* Create database cmd: CREATE DATABASE app_database;
* Connect to the database cmd: \connect app_database;
* List the table cmd: \dl

## Connecting PostgreSQL with PGAdmin
* Open the application click on the Register - Server
* Enter the any name under General tab
* Enter Host name/address as localhost, Port number 5432, Username <valid username> and Password <valid password> and click on save
* Create the Schemas in this case ‘myschema’
* Create the Database in this case ‘UserDatabase’
* Next, create table under the myschema as ‘user’ with details ID as bigint(PK), name as character(50), city as character(50), and password as character(20)

## Start the service 
* Right click and run the java main class 'src/main/java/com/example/springbootWithPostgresql/SpringbootWithPostgresqlApplication.java'

## How to install PostgreSQL on a Mac with Homebrew

### Step 1: Install Homebrew
Follow the instructions on their site.

### Step 2: Update Homebrew
Before you install anything with Homebrew, you should always make sure it’s up to date and that it’s healthy:
$ brew update
$ brew doctor

If you already had Homebrew installed, and brew doctor is reporting errors that you can’t fix on your own, Ruby on Mac comes with a “reset” mode that can safely back up and clean up your dev setup in 1 minute, and then you can run it in “normal” mode to reinstall everything from scratch in 15 minutes.

### Step 3: Install Postgres
$ brew install postgresql@14

When you install Postgres, Homebrew will provide useful information in your Terminal that you should read. Homebrew also helpfully creates a default database cluster. You can confirm that if you see something like this:

This formula has created a default database cluster with:
initdb --locale=C -E UTF-8 /opt/homebrew/var/postgresql@14

On an Intel Mac, you’ll see /usr/local instead of /opt/homebrew.

### Step 4: Start the Postgres service
$ brew services start postgresql@14

Wait a few seconds, then confirm that it’s running:
$ brew services list

It’s very important to confirm that it’s running because Homebrew might say Successfully started postgresql@14 when in fact it hasn’t. If it says started in green, then you should be all set to run the Rails commands to create and use the database in your app. If it says error in red, then read the Troubleshooting section below.

To stop Postgres:
$ brew services stop postgresql@14

Troubleshooting
If Postgres is failing to start, or if you see error when running brew services list, it’s usually due to one of these two issues:
* Postgres is already running. For example via the Postgres Mac app (the one with the blue elephant icon).
* You upgraded to a newer version of Postgres.

If you don’t have the Postgres Mac app, then read my guide about upgrading Postgres with Homebrew.

If you have the Postgres Mac app, and the server is already running, you won’t be able to use brew services to start Postgres because they both use port 5432 by default. Most people don’t need to run two Postgres servers at the same time. I recommend choosing either the Homebrew Postgres or the Postgres app. I personally prefer to manage everything with Homebrew to avoid conflicts.

To manage Postgres with Homebrew, make sure the server from the Postgres app is not running. You can check by launching the Postgres app. If the server is not running, the “Stop” button will be disabled and it should say “Not running”. If it is running, click the “Stop” button. Then, I recommend completely uninstalling the Postgres app using the instructions at the bottom of that linked page, then restart your computer. If you have any important data stored in a database listed in the Postgres app, back it up first.

If you prefer to use the Postgres app, then I recommend uninstalling Postgres from Homebrew:
brew services stop postgresql@14
brew uninstall --force postgresql@14

If you have a version of Postgres other than “14”, replace “14” in the commands above with the one shown when you run brew services list.
You might also need to remove any Postgres data saved with Homebrew (back it up first if there’s anything you don’t want to lose):

rm -rf $(brew --prefix)/var/postgresql@14
After removing Postgres from Homebrew, check that you can still access Postgres via the Postgres app:

psql

If it says “command not found” or “Unknown command”, you’ll need to add the Postgres app to your PATH in your shell file:

echo 'export PATH="/Applications/Postgres.app/Contents/Versions/latest/bin:$PATH"' >> ~/.zshrc

A misconfigured PATH is a common source of confusion and errors. There are plenty of instructions on the internet for setting the PATH, but very few explain what PATH does and why it needs to be updated. Read my guide about PATH to learn more.

Then quit and restart your Terminal (or open a new tab, or run source ~/.zshrc to refresh the file). Replace .zshrc with .bash_profile if you’re using the Bash shell. If you’re not sure, read my guide to find out which shell you’re using.