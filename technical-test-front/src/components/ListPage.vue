<template>
    <div style="margin-left: 40px;">
        <!-- create new products button -->
        <div>
            <h1 style="font-size: 300%;"><b>Product Browser</b></h1>
            <button class="btn btn-primary success add-product-btn" @click="createNewproduct">Ajouter un produit</button>
        </div>
        <br />
        <div class="container">
            <!-- list of all products -->
            <div>
                <ul>
                    <li v-for="product in products" :key="product.id">
                        <div class="name" @click="toggleDetails(product.numserie)" style="font-weight: bold;">
                            {{ product.name }}
                        </div>
                        <div class="details" v-if="product.showDetails" style="margin-bottom: 10px;">
                            <h3> {{ product.numserie }} </h3>
                            <br />
                            <p>{{ product.name }}</p>
                            <p>{{ product.price }}</p>
                            <p>{{ product.type }}</p>
                            <br />
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <CreateProductModal v-if="showCreateProductModal" @close="showCreateProductModal = false" />
</template>

<script>
import axios from 'axios';
import CreateProductModal from '@/components/CreateProductModal.vue';

export default {
    components: {
        CreateProductModal,
    },
    methods: {
        // method to get all products data and
        // push them on the products list once the page is opened
        // and that's why this method is called on the created()
        getProductList() {
            axios.get('http://localhost:8080/api/products/allProducts')
                .then(response => {
                    this.products = response.data;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        createNewproduct() {
            this.showCreateProductModal = true;
        },
        toggleDetails(productNumserie) {
            console.log(productNumserie);
            this.products.forEach(product => {
                if (product.numserie === productNumserie) {
                    product.showDetails = !product.showDetails;
                } else {
                    product.showDetails = false;
                }
            });
        }
  },
  data () {
    return {
        products: [],
        showCreateProductModal: false,
    }
  },
    created() {
        this.getProductList();
  },
    name: 'ListPage'
}
</script>

<style scoped>
ul {
    background-color: #f2f2f2;
    border-radius: 10px 0 0 10px;
    padding: 10px;
    list-style: none;
    display: inline-block;
}

.add-product-btn {
    background-color: green;
    color: white;
    border-radius: 5px;
    font-size: 16px;
    padding: 10px 20px;
    cursor: pointer;
}

.btn-group {
    display: flex;
    justify-content: flex-productt;
    align-items: center;
}

.edit-product-btn {
    background-color: blue;
    color: white;
    border-radius: 5px;
    font-size: 16px;
    padding: 10px 20px;
    cursor: pointer;
    margin-right: 10px;
}

.delete-product-btn {
    background-color: red;
    color: white;
    border-radius: 5px;
    font-size: 16px;
    padding: 10px 20px;
    cursor: pointer;
}

.name {
    cursor: pointer;
    font-weight: bold;
    display: inline-block;
}

.details {
    background-color: white;
    margin: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    position: absolute;
    top: 180px;
    /* initially collapse to the right */
    left: 140px;
    width: 400px;
    transition: all 0.3s ease;
    /* add transition effect */
}

/* expand the details section when clicked */
.details.expanded {
    right: 0;
}

@media (max-width: 767px) {

    /* keep the code the same if screen width is less than 768px */
    .details {
        position: static;
        right: 0;
        width: auto;
        margin: 10px 0;
    }

    h1 {
        margin-left: 100px;
    }
}
</style>
