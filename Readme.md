# javaDockerTask

## 🎯 Objective

Create a demo Java application, containerize it using a two-stage Dockerfile (build stage + runtime stage), and demonstrate the full lifecycle:

* Develop a simple Java application.
* Containerize with Docker (multi-stage build).
* Build and run the containerized app.
* Extract artifacts (compiled class or optionally source code) from the image/container.
* (Optional) Decompile `.class` files back to readable Java source.

---

## 1️⃣ Develop a Demo Java Application

**File: `BarqSystems.java`**

```java
public class BarqSystems {
    // ANSI escape codes for colors
    public static final String BLUE = "\u001B[34m";
    public static final String GRAY = "\u001B[37m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        // Print Header
        System.out.println(BLUE + "==============================");
        System.out.println("        BARQ SYSTEMS");
        System.out.println("==============================" + RESET);

        // Sub-header
        System.out.println(GRAY + "\n--- DEPARTMENT ---" + RESET);

        // Options
        System.out.println("1. DevOps");
        System.out.println("2. Data Analysis");
        System.out.println("3. Security");

        // End note
        System.out.println(BLUE + "\nThank you for choosing BARQ SYSTEMS!" + RESET);
    }
}
```

Local test before containerizing:

```bash
javac BarqSystems.java
java BarqSystems
```

---

## 2️⃣ Create a Two-Stage Dockerfile

**File: `Dockerfile`**

```dockerfile
#  Build
FROM openjdk:21-jdk-slim AS build
WORKDIR /app

# Copy code
COPY BarqSystems.java .

# Compile Java program
RUN javac BarqSystems.java

# Stage 2
FROM openjdk:21-jre-slim
WORKDIR /app

# Copy compiled class from build stage
COPY --from=build /app/BarqSystems.class .

# Run the application
CMD ["java", "BarqSystems"]
```

---

## 3️⃣ Build the Docker Image

```bash
docker build -t java-demo-app .
```

---

## 4️⃣ Run the Application in a Container

```bash
docker run --rm java-demo-app
```

✅ Expected Output:

```
==============================
        BARQ SYSTEMS
==============================

--- DEPARTMENT ---
1. DevOps
2. Data Analysis
3. Security

Thank you for choosing BARQ SYSTEMS!
```

---

## 5️⃣ Extract Artifacts from the Image

### Option A: Copy from a container

```bash
docker create --name temp java-demo-app
docker cp temp:/app/BarqSystems.class ./RecoveredBarqSystems.class
docker rm temp
```

### Option B: Include Source in Image (Demo Only)

If you want the `.java` source file recoverable (for demo/learning), modify Dockerfile to also copy it:

```dockerfile
COPY --from=build /app/BarqSystems.java .
```

Then you can extract it:

```bash
docker run --rm java-demo-app cat /app/BarqSystems.java > RecoveredBarqSystems.java
```

---

## 6️⃣ Decompile `.class` to Source (Optional)

On Linux, install a Java decompiler like [CFR](https://www.benf.org/other/cfr/):

```bash
wget https://www.benf.org/other/cfr/cfr-0.152.jar -O cfr.jar
java -jar cfr.jar RecoveredBarqSystems.class --outputdir ./decompiled
```

This will generate a readable `.java` file.

---

## ✅ Lifecycle Covered

1. **Develop Java app** (`BarqSystems.java`)
2. **Containerize** (multi-stage Dockerfile with JDK → JRE)
3. **Build + Run** the container
4. **Extract artifacts** (compiled `.class` or source)
5. **Optionally decompile** for recovery

