<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['order:item:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:item:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:item:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['order:item:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="明细ID" align="center" prop="id" />
      <el-table-column label="订单ID" align="center" prop="orderId" />
      <el-table-column label="订单编号" align="center" prop="orderNo" />
      <el-table-column label="商品ID" align="center" prop="productId" />
      <el-table-column label="SKU ID" align="center" prop="skuId" />
      <el-table-column label="商品名称" align="center" prop="productName" />
      <el-table-column label="SKU规格" align="center" prop="skuSpec" />
      <el-table-column label="商品图片" align="center" prop="productImage" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.productImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="原价" align="center" prop="price" />
      <el-table-column label="实际价格" align="center" prop="actualPrice" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="总金额" align="center" prop="totalAmount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['order:item:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['order:item:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改订单明细管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="itemRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单ID" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单ID" />
        </el-form-item>
        <el-form-item label="订单编号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="商品ID" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="SKU ID" prop="skuId">
          <el-input v-model="form.skuId" placeholder="请输入SKU ID" />
        </el-form-item>
        <el-form-item label="商品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="SKU规格" prop="skuSpec">
          <el-input v-model="form.skuSpec" placeholder="请输入SKU规格" />
        </el-form-item>
        <el-form-item label="商品图片" prop="productImage">
          <image-upload v-model="form.productImage"/>
        </el-form-item>
        <el-form-item label="原价" prop="price">
          <el-input v-model="form.price" placeholder="请输入原价" />
        </el-form-item>
        <el-form-item label="实际价格" prop="actualPrice">
          <el-input v-model="form.actualPrice" placeholder="请输入实际价格" />
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="总金额" prop="totalAmount">
          <el-input v-model="form.totalAmount" placeholder="请输入总金额" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Item">
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/order/item";

const { proxy } = getCurrentInstance();

const itemList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    orderNo: null,
    productName: null,
  },
  rules: {
    orderId: [
      { required: true, message: "订单ID不能为空", trigger: "blur" }
    ],
    orderNo: [
      { required: true, message: "订单编号不能为空", trigger: "blur" }
    ],
    productId: [
      { required: true, message: "商品ID不能为空", trigger: "blur" }
    ],
    skuId: [
      { required: true, message: "SKU ID不能为空", trigger: "blur" }
    ],
    productName: [
      { required: true, message: "商品名称不能为空", trigger: "blur" }
    ],
    price: [
      { required: true, message: "原价不能为空", trigger: "blur" }
    ],
    actualPrice: [
      { required: true, message: "实际价格不能为空", trigger: "blur" }
    ],
    quantity: [
      { required: true, message: "数量不能为空", trigger: "blur" }
    ],
    totalAmount: [
      { required: true, message: "总金额不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询订单明细管理列表 */
function getList() {
  loading.value = true;
  listItem(queryParams.value).then(response => {
    itemList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    orderId: null,
    orderNo: null,
    productId: null,
    skuId: null,
    productName: null,
    skuSpec: null,
    productImage: null,
    price: null,
    actualPrice: null,
    quantity: null,
    totalAmount: null,
    createTime: null
  };
  proxy.resetForm("itemRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加订单明细管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getItem(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改订单明细管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["itemRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateItem(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addItem(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除订单明细管理编号为"' + _ids + '"的数据项？').then(function() {
    return delItem(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('order/item/export', {
    ...queryParams.value
  }, `item_${new Date().getTime()}.xlsx`)
}

getList();
</script>
