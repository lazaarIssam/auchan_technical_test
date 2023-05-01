<template>
    <div class="modal-backdrop">
        <div class="modalview">
            <header class="modal-header">
                <slot name="header">
                    Ajoutez un star
                </slot>
            </header>

          <form @submit.prevent="createProduct">
            <section class="modal-body">
              <div class="form-group">
                <label for="name">name:</label>
                <input type="text" class="form-control" id="name" v-model="name">
              </div>

              <div class="form-group">
                <label for="numserie">numserie:</label>
                <input type="text" class="form-control" id="numserie" v-model="numserie">
              </div>

              <div class="form-group">
                  <label for="numserie">price:</label>
                  <input type="text" class="form-control" id="price" v-model="price">
                </div>

              <div class="form-group">
                <label for="type">type:</label>
                <input type="text" class="form-control" id="type" v-model="type">
              </div>
            </section>

            <footer class="modal-footer text-right">
              <button type="submit" class="btn-green">Enregistrer</button>
              <button type="button" class="btn-green" @click="close">Fermer</button>
            </footer>
          </form>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
  data () {
        return {
        name: '',
        numserie: '',
        price: '',
        type: ''
    }
  },
    name: 'CreateProductModal',
    methods: {
        close() {
            this.$emit('close');
        },
        createProduct() {
            axios.post('http://localhost:8080/api/products/createProduct', {
                name: this.name,
                numserie: this.numserie,
                price: this.price,
                type: this.type}, {
                headers: {
                    'Content-Type': 'application/json',
                },
            }
            ).then(response => {
                // handle success response
                console.log(response.data);
                // reload the page once it's done
                window.location.reload()
                // emit an event to close the modal
                this.$emit('close');
            })
            .catch(error => {
                // handle error response
                console.log(error.response.data);
            });
        }
    },
};
</script>

<style>
.modal-backdrop {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
}

.modalview {
    background: #FFFFFF;
    box-shadow: 2px 2px 20px 1px;
    overflow-x: auto;
    display: flex;
    width: fit-content;
    height: fit-content;
    flex-direction: column;
}

.modal-header {
    padding: 15px;
    display: flex;
    justify-content: center;
}

.modal-footer {
    padding: 15px;
    justify-content: center;
}

.modal-header {
    position: relative;
    border-bottom: 1px solid #eeeeee;
    color: #4AAE9B;
    justify-content: space-between;
}

/* .modal-footer {
    border-top: 1px solid #eeeeee;
    flex-direction: column;
    justify-content: flex-end;
} */

.modal-body {
    position: relative;
    padding: 20px 10px;
}

.btn-green {
    color: white;
    background: #4AAE9B;
    border: 1px solid #4AAE9B;
    border-radius: 2px;
    width: 100px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.form-group {
  margin: 10px;
  width: 100%;
}

label {
  font-weight: bold;
}

input[type="text"],
textarea {
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #f5f5f5;
  width: 100%;
  box-sizing: border-box;
  font-size: 16px;
  margin-top: 5px;
}

input[type="file"] {
  margin-top: 5px;
}

button[type="submit"],
button[type="button"] {
  margin: 10px;
  padding: 10px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
}

.btn-green {
  background-color: #4CAF50;
  color: white;
}

.modal-body {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.modal-footer {
  width: 100%;
  margin-left: auto;
}

</style>