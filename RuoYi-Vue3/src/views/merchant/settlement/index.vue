<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="结算单号" prop="settlementNo">
        <el-input
          v-model="queryParams.settlementNo"
          placeholder="请输入结算单号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结算开始日期" prop="settlementStart">
        <el-date-picker clearable
          v-model="queryParams.settlementStart"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择结算开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结算结束日期" prop="settlementEnd">
        <el-date-picker clearable
          v-model="queryParams.settlementEnd"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择结算结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结算时间" prop="settlementTime">
        <el-date-picker clearable
          v-model="queryParams.settlementTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择结算时间">
        </el-date-picker>
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
          v-hasPermi="['merchant:settlement:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['merchant:settlement:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['merchant:settlement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['merchant:settlement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="settlementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="结算ID" align="center" prop="id" />
      <el-table-column label="商家ID" align="center" prop="merchantId" />
      <el-table-column label="结算单号" align="center" prop="settlementNo" />
      <el-table-column label="结算开始日期" align="center" prop="settlementStart" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.settlementStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结算结束日期" align="center" prop="settlementEnd" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.settlementEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单数量" align="center" prop="orderCount" />
      <el-table-column label="结算总金额" align="center" prop="totalAmount" />
      <el-table-column label="平台分成" align="center" prop="platformAmount" />
      <el-table-column label="商家分成" align="center" prop="merchantAmount" />
      <el-table-column label="状态：0待结算，1已结算" align="center" prop="status" />
      <el-table-column label="结算时间" align="center" prop="settlementTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.settlementTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['merchant:settlement:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['merchant:settlement:remove']">删除</el-button>
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

    <!-- 添加或修改商家结算管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="settlementRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商家ID" prop="merchantId">
          <el-input v-model="form.merchantId" placeholder="请输入商家ID" />
        </el-form-item>
        <el-form-item label="结算单号" prop="settlementNo">
          <el-input v-model="form.settlementNo" placeholder="请输入结算单号" />
        </el-form-item>
        <el-form-item label="结算开始日期" prop="settlementStart">
          <el-date-picker clearable
            v-model="form.settlementStart"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择结算开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结算结束日期" prop="settlementEnd">
          <el-date-picker clearable
            v-model="form.settlementEnd"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择结算结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单数量" prop="orderCount">
          <el-input v-model="form.orderCount" placeholder="请输入订单数量" />
        </el-form-item>
        <el-form-item label="结算总金额" prop="totalAmount">
          <el-input v-model="form.totalAmount" placeholder="请输入结算总金额" />
        </el-form-item>
        <el-form-item label="平台分成" prop="platformAmount">
          <el-input v-model="form.platformAmount" placeholder="请输入平台分成" />
        </el-form-item>
        <el-form-item label="商家分成" prop="merchantAmount">
          <el-input v-model="form.merchantAmount" placeholder="请输入商家分成" />
        </el-form-item>
        <el-form-item label="结算时间" prop="settlementTime">
          <el-date-picker clearable
            v-model="form.settlementTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择结算时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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

<script setup name="Settlement">
import { listSettlement, getSettlement, delSettlement, addSettlement, updateSettlement } from "@/api/merchant/settlement";

const { proxy } = getCurrentInstance();

const settlementList = ref([]);
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
    settlementNo: null,
    settlementStart: null,
    settlementEnd: null,
    status: null,
    settlementTime: null,
  },
  rules: {
    merchantId: [
      { required: true, message: "商家ID不能为空", trigger: "blur" }
    ],
    settlementNo: [
      { required: true, message: "结算单号不能为空", trigger: "blur" }
    ],
    settlementStart: [
      { required: true, message: "结算开始日期不能为空", trigger: "blur" }
    ],
    settlementEnd: [
      { required: true, message: "结算结束日期不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询商家结算管理列表 */
function getList() {
  loading.value = true;
  listSettlement(queryParams.value).then(response => {
    settlementList.value = response.rows;
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
    merchantId: null,
    settlementNo: null,
    settlementStart: null,
    settlementEnd: null,
    orderCount: null,
    totalAmount: null,
    platformAmount: null,
    merchantAmount: null,
    status: null,
    settlementTime: null,
    remark: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("settlementRef");
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
  title.value = "添加商家结算管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getSettlement(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改商家结算管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["settlementRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSettlement(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSettlement(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除商家结算管理编号为"' + _ids + '"的数据项？').then(function() {
    return delSettlement(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('merchant/settlement/export', {
    ...queryParams.value
  }, `settlement_${new Date().getTime()}.xlsx`)
}

getList();
</script>
