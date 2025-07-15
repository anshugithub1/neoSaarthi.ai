# 🧽 neoSaarthi – Your Intelligent Web productivity tool

**neoSaarthi** is an advanced, full-stack Chrome Extension that enhances your web productivity by helping you consume information-heavy content effortlessly. Whether you’re reading technical documentation, articles, or lengthy blogs — neoSaarthi acts as your digital guide by summarizing content and recommending semantically related reads.

> 🔧 Built with **Spring Boot** for intelligent REST API integration and designed with modularity for extensibility.

---

## 🚀 Key Features

- 📝 **Smart Summarization**\
  Extracts key points from large web pages using backend-powered NLP.

- 🔍 **Contextual Recommendations**\
  Finds related reads for deeper exploration on the topic.

- 📆 **Modular Backend (Spring Boot)**\
  REST API architecture makes it easy to plug in new features (e.g., GPT, bookmarks, highlighting).

- ⚙️ **Runs Offline (Unpacked Install)**\
  No Chrome Store needed. Full support for manual install.

---
## 🖼 UI Previews
<img width="1920" height="1080" alt="Screenshot 2025-07-15 125654" src="https://github.com/user-attachments/assets/d0302002-5913-4b9a-abca-79094233661b" />

---

## 🧠 Tech Stack

| Layer       | Tech Used                    |
| ----------- | ---------------------------- |
| Backend API | Java, Spring Boot, Maven     |
| Frontend    | HTML, js, CSS, Chrome APIs   |
| NLP Engine  | Gemini NLP model             |
| Storage     | Chrome Local Storage         |

---

## 🧠 Installation Guide (Local Setup)

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/neoSaarthi.git
cd neoSaarthi
```

---

### 2. Run Spring Boot Backend

```bash
cd backend
mvn spring-boot:run
```

- Ensure Java 17+ and Maven are installed
- This starts the REST APIs used for summarization and recommendations

---

### 3. Load Chrome Extension

- Open Google Chrome and go to `chrome://extensions`
- Enable **Developer Mode** (top-right toggle)
- Click **"Load Unpacked"** and select the `extension/` folder
- You should now see neoSaarthi in your browser toolbar ✅

---



### 🔹 Popup Window
<img width="852" height="888" alt="nSImage" src="https://github.com/user-attachments/assets/3635ecf3-8aa3-4295-9e8a-732152514de4" />



### 🔹 Summarized Output Example



---

## 📁 Folder Structure

```
neoSaarthi/
├── backend/                   # Spring Boot project
│   └── src/...
├── extension/                 # Chrome extension UI
│   ├── manifest.json
│   ├── popup.html
│   ├── content.js
│   ├── popup.js
│   └── styles/
│       └── popup.css
├── assets/                    # Images and icons
│   ├── banner.png
│   └── popup.png
└── utils/
    └── summarizer.js          # Core summarization logic
```

---

## 📌 Ideal Use Cases

- 👨‍💻 Developers browsing large API docs or framework guides
- 🎓 Students summarizing long academic readings
- 👩‍🏫 Researchers and knowledge workers navigating complex topics

---

## 🧡 Behind the Name

> "Saarthi" means **guide** or **navigator** in Sanskrit.\
> **neoSaarthi** is your modern, intelligent guide for the information highway.

---

## 🗓 Roadmap

-

---

## 🧳️ Contribution

Want to add a feature or fix a bug? We welcome contributions!

```bash
# Fork the repo
# Create a new branch
# Submit a pull request 🚀
```

Or open an issue if you have ideas or feedback.

---

## 🛡 License

This project is licensed under the [MIT License](./LICENSE).

---

## 📬 Contact

- Email: [**anshusaurabh1ay@gmail.com**](mailto\:anshusaurabh1ay@gmail.com)
- GitHub: [@anshugithub1](https://github.com/anshugithub1)

---

> ✨ **Navigate smarter. Learn faster. Explore deeper — with neoSaarthi.**

