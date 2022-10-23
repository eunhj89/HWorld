import { initializeApp } from "https://www.gstatic.com/firebasejs/9.12.1/firebase-app.js";
import { getAuth, onAuthStateChanged, signInAnonymously } from "https://www.gstatic.com/firebasejs/9.12.1/firebase-auth.js";
import { getDatabase, ref } from "https://www.gstatic.com/firebasejs/9.12.1/firebase-database.js";
export { set, remove, update, onDisconnect, onValue, onChildAdded, onChildChanged, onChildRemoved  } from "https://www.gstatic.com/firebasejs/9.12.1/firebase-database.js";
// Your web app's Firebase configuration
const firebaseConfig = {
    ...
};

// Initialize Firebase
const firebase = initializeApp(firebaseConfig);
const auth = getAuth(firebase);
const database = getDatabase(firebase);

export function onAuthChanged(user) {
    return onAuthStateChanged(auth, user);
}

export function signIn() {
    return signInAnonymously(auth);
}

export const getRef = (refUrl) => {
    return ref(database, refUrl);
}
