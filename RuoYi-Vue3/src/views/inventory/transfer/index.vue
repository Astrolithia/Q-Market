<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="调拨单号" prop="transferNo">
        <el-input
          v-model="queryParams.transferNo"
          placeholder="请输入调拨单号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="调出仓库ID" prop="outWarehouseId">
        <el-input
          v-model="queryParams.outWarehouseId"
          placeholder="请输入调出仓库ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="调入仓库ID" prop="inWarehouseId">
        <el-input
          v-model="queryParams.inWarehouseId"
          placeholder="请输入调入仓库ID"
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
          v-hasPermi="['inventory:transfer:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['inventory:transfer:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['inventory:transfer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['inventory:transfer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="transferList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="调拨单ID" align="center" prop="id" />
      <el-table-column label="调拨单号" align="center" prop="transferNo" />
      <el-table-column label="调出仓库ID" align="center" prop="outWarehouseId" />
      <el-table-column label="调入仓库ID" align="center" prop="inWarehouseId" />
      <el-table-column label="调拨数量" align="center" prop="totalQuantity" />
      <el-table-column label="状态：0草稿，1待出库，2已出库待入库，3已完成，4已取消" align="center" prop="status" />
      <el-table-column label="操作人" align="center" prop="operator" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary"  @click="handleUpdate(scope.row)" v-hasPermi="['inventory:transfer:edit']">修改</el-button>
          <el-button link type="primary"  @click="handleDelete(scope.row)" v-hasPermi="['inventory:transfer:remove']">取消</el-button>
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

    <!-- 添加或修改库存调拨对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="transferRef" :model="form" :rules="rules" label-width="80px">
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

<script setup name="Transfer">
import { listTransfer, getTransfer, delTransfer, addTransfer, updateTransfer } from "@/api/inventory/transfer";

const { proxy } = getCurrentInstance();

const transferList = ref([]);
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
    transferNo: null,
    outWarehouseId: null,
    inWarehouseId: null,
    status: null,
  },
  rules: {
    transferNo: [
      { required: true, message: "调拨单号不能为空", trigger: "blur" }
    ],
    outWarehouseId: [
      { required: true, message: "调出仓库ID不能为空", trigger: "blur" }
    ],
    inWarehouseId: [
      { required: true, message: "调入仓库ID不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询库存调拨列表 */
function getList() {
  loading.value = true;
  listTransfer(queryParams.value).then(response => {
    transferList.value = response.rows;
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
    transferNo: null,
    outWarehouseId: null,
    inWarehouseId: null,
    merchantId: null,
    status: null,
    totalQuantity: null,
    totalAmount: null,
    operator: null,
    operatorId: null,
    outTime: null,
    inTime: null,
    outOperator: null,
    inOperator: null,
    outStockNo: null,
    inStockNo: null,
    reason: null,
    remark: null,
    createTime: null,
    updateTime: null,
    createBy: null,
    updateBy: null
  };
  proxy.resetForm("transferRef");
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
  title.value = "添加库存调拨";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getTransfer(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改库存调拨";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["transferRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateTransfer(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addTransfer(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除库存调拨编号为"' + _ids + '"的数据项？').then(function() {
    return delTransfer(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('inventory/transfer/export', {
    ...queryParams.value
  }, `transfer_${new Date().getTime()}.xlsx`)
}

getList();
</script>
