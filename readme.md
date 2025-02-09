## How to Run

### Step 1: Start the lessonmanagement

```bash
cd lessonmanagement
mvn spring-boot:run
```

### Step 2: Run the CLI

```bash
cd ../cli
mvn clean package
```

### Step 3: Use the CLI (from root)

Create new item

```bash
java -jar cli/target/cli-1.0-SNAPSHOT.jar create -n "New Item" -d "This is a test item."
```

List all items

```bash
java -jar cli/target/cli-1.0-SNAPSHOT.jar list
```

Get an item by id

```bash
java -jar cli/target/cli-1.0-SNAPSHOT.jar get -i 1
```

**Replace 1 with desired id**

Delete an item

```bash
java -jar cli/target/cli-1.0-SNAPSHOT.jar delete -i 1
```

**Replace 1 with desired id**

## Info

- Currently using H2 in-memory database only.
- The lessonmanagement is running on port 8080.
