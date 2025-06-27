<template>
  <div class="container">
    <h1>Geometrický tvar</h1>
    <button @click="loadShape">Načíst tvar</button>

    <div class="fields">
      <div>
        <h3>Původní odpověď</h3>
        <textarea v-model="originalShape" readonly rows="8" cols="50" />
      </div>

      <div>
        <h3>Dopočítaný tvar</h3>
        <textarea v-model="completedShape" readonly rows="8" cols="50" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      originalShape: '',
      completedShape: ''
    }
  },
  methods: {
    async loadShape() {
      try {
        const res = await fetch('/api/shape/full', { method: 'POST' });
        const shape = await res.json();

        //vytvoření "originálu", fuj fuj řešení, možná fixnu možná ne
        const original = { ...shape };

        const attrToRemove = original.missingAttribute;
        if (attrToRemove && Object.prototype.hasOwnProperty.call(original, attrToRemove)) {
          delete original[attrToRemove];
        }
        //odstranění missing atributu
        delete original.missingAttribute;
        delete shape.missingAttribute;

        this.originalShape = JSON.stringify(original, null, 2);
        this.completedShape = JSON.stringify(shape, null, 2);
      } catch (error) {
        this.originalShape = 'Chyba při načítání tvaru.';
        this.completedShape = '';
      }
    }
  }
}
</script>

<style scoped>
.container {
  padding: 1rem;
}
.fields {
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
}
textarea {
  width: 100%;
  font-family: monospace;
}
</style>
