<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="仓库ID" prop="warehouseId">
        <el-input
          v-model="queryParams.warehouseId"
          placeholder="请输入仓库ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品ID" prop="productId">
        <el-input
          v-model="queryParams.productId"
          placeholder="请输入商品ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SKU ID" prop="skuId">
        <el-input
          v-model="queryParams.skuId"
          placeholder="请输入SKU ID"
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
          v-hasPermi="['inventory:inventory:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['inventory:inventory:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['inventory:inventory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['inventory:inventory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inventoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="库存ID" align="center" prop="id" />
      <el-table-column label="仓库ID" align="center" prop="warehouseId" />
      <el-table-column label="商品ID" align="center" prop="productId" />
      <el-table-column label="SKU ID" align="center" prop="skuId" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="锁定数量" align="center" prop="lockQuantity" />
      <el-table-column label="可用数量" align="center" prop="availableQuantity" />
      <el-table-column label="预警数量" align="center" prop="alertQuantity" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['inventory:inventory:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['inventory:inventory:remove']">删除</el-button>
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

    <!-- 添加或修改库存查询对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="inventoryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="仓库ID" prop="warehouseId">
          <el-input v-model="form.warehouseId" placeholder="请输入仓库ID" />
        </el-form-item>
        <el-form-item label="商品ID" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="SKU ID" prop="skuId">
          <el-input v-model="form.skuId" placeholder="请输入SKU ID" />
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="锁定数量" prop="lockQuantity">
          <el-input v-model="form.lockQuantity" placeholder="请输入锁定数量" />
        </el-form-item>
        <el-form-item label="可用数量" prop="availableQuantity">
          <el-input v-model="form.availableQuantity" placeholder="请输入可用数量" />
        </el-form-item>
        <el-form-item label="预警数量" prop="alertQuantity">
          <el-input v-model="form.alertQuantity" placeholder="请输入预警数量" />
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

<script setup name="Inventory">
import { listInventory, getInventory, delInventory, addInventory, updateInventory } from "@/api/inventory/inventory";

const { proxy } = getCurrentInstance();

const inventoryList = ref([]);
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
    warehouseId: null,
    productId: null,
    skuId: null,
  },
  rules: {
    warehouseId: [
      { required: true, message: "仓库ID不能为空", trigger: "blur" }
    ],
    productId: [
      { required: true, message: "商品ID不能为空", trigger: "blur" }
    ],
    skuId: [
      { required: true, message: "SKU ID不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询库存查询列表 */
function getList() {
  loading.value = true;
  listInventory(queryParams.value).then(response => {
    inventoryList.value = response.rows;
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
    warehouseId: null,
    productId: null,
    skuId: null,
    quantity: null,
    lockQuantity: null,
    availableQuantity: null,
    alertQuantity: null,
    costPrice: null,
    merchantId: null,
    updateTime: null
  };
  proxy.resetForm("inventoryRef");
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
  title.value = "添加库存查询";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getInventory(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改库存查询";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["inventoryRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateInventory(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addInventory(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除库存查询编号为"' + _ids + '"的数据项？').then(function() {
    return delInventory(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('inventory/inventory/export', {
    ...queryParams.value
  }, `inventory_${new Date().getTime()}.xlsx`)
}

getList();
</script>
