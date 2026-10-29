//
// Luis Llopis, Data Structures, UMA.
//

#include "Task.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Task* Task_new(unsigned int id, const char* name, int quantum) {
  struct Task* newTask;

  newTask = malloc(sizeof(struct Task));
  if (strlen(name) <= MAX_NAME_LEN) {
    newTask->id = id;
    strcpy(newTask->name, name);
    newTask->quantum = quantum;
  }

  return newTask;
}

void Task_free(struct Task** p_p_task) {
  if (p_p_task != NULL && *p_p_task != NULL) {
    free(*p_p_task);
    *p_p_task = NULL;
  }
}

struct Task* Task_copyOf(const struct Task* p_task) {
  struct Task* newTask;
  newTask = malloc(sizeof(struct Task));

  if (p_task != NULL) {
    newTask->id = p_task->id;
    strcpy(newTask->name, p_task->name);
    newTask->quantum = p_task->quantum;
  }

  return newTask;
}

void Task_print(const struct Task* p_task) {
  printf("%d, %s, %d", p_task->id, p_task->name, p_task->quantum);
}