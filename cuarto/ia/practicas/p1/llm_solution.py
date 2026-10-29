##Here's a well-structured Python script that fulfills the specified requirements using pandas and scikit-learn:

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score, f1_score, confusion_matrix

# Load the dataset
df = pd.read_csv('creditcard_subset.csv')

# Separate features and target
X = df.drop('Class', axis=1)
y = df['Class']

# Split the dataset into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(
    X, y, 
    test_size=0.2, 
    random_state=42, 
    stratify=y
)

# Initialize and train the Decision Tree classifier
clf = DecisionTreeClassifier(max_depth=5, random_state=42)
clf.fit(X_train, y_train)

# Make predictions on the test set
y_pred = clf.predict(X_test)

# Calculate evaluation metrics
accuracy = accuracy_score(y_test, y_pred)
f1 = f1_score(y_test, y_pred, average='binary', pos_label=1)
conf_matrix = confusion_matrix(y_test, y_pred)

# Print the results
print(f'Accuracy: {accuracy:.4f}')
print(f'F1 Score: {f1:.4f}')
print('Confusion Matrix:')
print(conf_matrix)


""" ### ✅ Explanation of Key Components:

1. **Data Loading**:
   - Uses `pandas` to load the `creditcard_subset.csv` file.
   - Splits the dataset into features (`X`) and target (`y`), excluding the 'Class' column from the features.

2. **Data Splitting**:
   - Uses `train_test_split` with `stratify=y` to maintain class distribution in both training and test sets.
   - Ensures reproducibility with `random_state=42`.

3. **Model Training**:
   - Initializes a `DecisionTreeClassifier` with a maximum depth of 5.
   - Fits the model on the training data.

4. **Evaluation Metrics**:
   - **Accuracy**: Measures how often the classifier makes the correct prediction.
   - **F1 Score**: Harmonic mean of precision and recall, suitable for imbalanced datasets.
   - **Confusion Matrix**: Shows the number of true positives, true negatives, false positives, and false negatives.

5. **Output**:
   - Prints the accuracy, F1 score, and confusion matrix in a clear and readable format.

---

### 📌 Notes:
- The script assumes that the `Class` column contains binary labels (e.g., 0 and 1).
- The `pos_label=1` argument in `f1_score` ensures the metric is calculated for the positive class (fraudulent transactions).
- The confusion matrix is printed as a NumPy array, which is standard for scikit-learn.

This script is ready to be run in any environment with pandas and scikit-learn installed. """